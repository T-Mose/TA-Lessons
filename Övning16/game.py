#!/usr/bin/env python3

import requests
import random

VERSION_GROUP = "scarlet-violet"  # or "sword-shield", "red-blue", etc.

class Move:
    def __init__(self, name, power, accuracy):
        self.name = name
        self.power = power
        self.accuracy = accuracy

class Pokemon:
    def __init__(self, name, hp, attack, defense, speed, moves):
        self.name = name
        self.hp = hp
        self.attack = attack
        self.defense = defense
        self.speed = speed
        self.moves = moves  # list of Move objects

    def reduce_hp(self, amount):
        self.hp = max(0, self.hp - amount)

def fetch_move_details(move_name: str):
    """Fetch a move's power & accuracy from /move/{moveName}."""
    url = f"https://pokeapi.co/api/v2/move/{move_name.lower()}"
    resp = requests.get(url)
    if resp.status_code != 200:
        # fallback
        return 50, 100

    data = resp.json()
    power = data["power"] if data["power"] is not None else 0
    accuracy = data["accuracy"] if data["accuracy"] is not None else 100
    return power, accuracy

def fetch_pokemon(pokemon_name: str):
    """
    Fetch a Pokémon's real stats (HP, Attack, Defense, Speed) from /pokemon/{pokemonName}.
    Then filter its moves to those learned by 'level-up' in the given VERSION_GROUP,
    pick the 4 highest-level moves, and fetch each move's real power & accuracy.
    Returns a Pokemon object, or None if not found.
    """
    pokemon_url = f"https://pokeapi.co/api/v2/pokemon/{pokemon_name.lower()}"
    resp = requests.get(pokemon_url)

    if resp.status_code != 200:
        print(f"ERROR: Pokémon '{pokemon_name}' not found!")
        return None

    data = resp.json()

    # Extract real stats from the "stats" array
    # Example: "stat": {"name":"hp"}, "base_stat": 48
    stats_map = {s["stat"]["name"]: s["base_stat"] for s in data["stats"]}
    hp = stats_map.get("hp", 50)
    attack = stats_map.get("attack", 50)
    defense = stats_map.get("defense", 50)
    speed = stats_map.get("speed", 50)

    # The "species" name for display (capitalized)
    species_name = data["species"]["name"].capitalize()

    # 1) Gather all level-up moves for the specified version group
    level_up_moves = []
    for m in data["moves"]:
        move_name = m["move"]["name"]  # e.g. "tackle"
        version_details = m["version_group_details"]
        for v in version_details:
            # We'll look for our chosen version group (e.g. "scarlet-violet")
            # and the method "level-up"
            if (
                v["version_group"]["name"] == VERSION_GROUP
                and v["move_learn_method"]["name"] == "level-up"
            ):
                level_learned = v["level_learned_at"]
                level_up_moves.append((move_name, level_learned))
                break  # no need to check other version_details for this move

    # 2) Sort by level_learned_at, descending (highest-level moves last)
    level_up_moves.sort(key=lambda x: x[1], reverse=True)

    # 3) Take up to 4 of the highest-level moves
    chosen_moves = level_up_moves[:4]

    # If no moves found in that version group, fallback to the first 4 moves in the entire array
    if not chosen_moves:
        print(f"No level-up moves found for {species_name} in {VERSION_GROUP}!")
        # fallback: just take the first 4 from data["moves"]
        chosen_moves = [(m["move"]["name"], 0) for m in data["moves"][:4]]

    # 4) For each chosen move, fetch real power & accuracy from /move/{move}
    move_objs = []
    for (mv_name, _) in chosen_moves:
        power, accuracy = fetch_move_details(mv_name)
        # Capitalize move name for nice display
        nice_name = mv_name.capitalize()
        move_objs.append(Move(nice_name, power, accuracy))

    return Pokemon(species_name, hp, attack, defense, speed, move_objs)

def choose_move(pokemon: Pokemon):
    """
    Let the player pick from the Pokémon's moves (manual PvP).
    If no moves, return a default "Struggle" move.
    """
    if not pokemon.moves:
        print(f"{pokemon.name} has no moves! Using Struggle.")
        return Move("Struggle", 50, 100)

    while True:
        print(f"\n{pokemon.name}'s turn! Choose a move:")
        for i, mv in enumerate(pokemon.moves):
            print(f"{i+1}. {mv.name} (Power={mv.power}, Accuracy={mv.accuracy})")

        choice_str = input("Enter move number: ")
        try:
            choice = int(choice_str) - 1
            if 0 <= choice < len(pokemon.moves):
                return pokemon.moves[choice]
        except ValueError:
            pass
        print("Invalid choice. Try again.")

def calculate_damage(attacker: Pokemon, defender: Pokemon, move: Move):
    """
    Simplified Pokémon damage formula with a fixed level=50:
      damage = (((2*50)/5 + 2) * (Atk * Power / Def)) / 50 + 2
    """
    if move.power <= 0:
        return 0

    level = 50
    numerator = ((2 * level) / 5 + 2) * attacker.attack * move.power / defender.defense
    dmg = numerator / 50 + 2
    return max(1, int(dmg))

def battle(p1: Pokemon, p2: Pokemon):
    """
    Turn-based manual PvP. Each Pokémon picks moves until one faints.
    """
    print("\n=== BATTLE START ===")
    print(f"{p1.name} (HP={p1.hp})  VS  {p2.name} (HP={p2.hp})")

    # Speed decides who goes first
    p1_turn = (p1.speed >= p2.speed)

    while p1.hp > 0 and p2.hp > 0:
        attacker = p1 if p1_turn else p2
        defender = p2 if p1_turn else p1

        # Choose a move
        mv = choose_move(attacker)
        print(f"{attacker.name} used {mv.name}!")

        # Accuracy check
        roll = random.randint(1, 100)
        if roll <= mv.accuracy:
            dmg = calculate_damage(attacker, defender, mv)
            defender.reduce_hp(dmg)
            print(f"{defender.name} took {dmg} damage! (HP left: {defender.hp})")
        else:
            print("It missed!")

        if defender.hp <= 0:
            print(f"{defender.name} fainted!")
            break

        p1_turn = not p1_turn

    # Determine winner
    if p1.hp <= 0 and p2.hp <= 0:
        print("It's a tie!")
    elif p1.hp <= 0:
        print(f"{p2.name} wins!")
    else:
        print(f"{p1.name} wins!")

    print("=== BATTLE END ===\n")

def main():
    print("Welcome to the Pokémon PvP Battle (with real final moves)!")
    print(f"(Version group set to: {VERSION_GROUP})\n")

    name1 = input("Enter the first Pokémon's name: ").strip()
    name2 = input("Enter the second Pokémon's name: ").strip()

    p1 = fetch_pokemon(name1)
    p2 = fetch_pokemon(name2)

    # If either is None, fallback to defaults
    if p1 is None:
        print(f"Falling back to Pikachu for {name1}!")
        p1 = Pokemon("Pikachu", 35, 55, 40, 90, [
            Move("Thunder Shock", 40, 100),
            Move("Quick Attack", 40, 100),
        ])

    if p2 is None:
        print(f"Falling back to Bulbasaur for {name2}!")
        p2 = Pokemon("Bulbasaur", 45, 49, 49, 45, [
            Move("Vine Whip", 45, 100),
            Move("Tackle", 40, 95),
        ])

    battle(p1, p2)

if __name__ == "__main__":
    main()
