from graphviz import Digraph

# Create a new directed graph
dot = Digraph(comment='School System Mind Map', format='png')
dot.attr(size='10,10')

# Central Node
dot.node('School System', 'School System')

# Primary Nodes
dot.node('Person', 'Person (Base Class)')
dot.node('Teachable', 'Teachable (Interface)')
dot.node('Evaluable', 'Evaluable (Interface)')
dot.node('Course', 'Course')
dot.node('Department', 'Department')
dot.node('Classroom', 'Classroom')

# Secondary Nodes (Subclasses of Person)
dot.node('Student', 'Student')
dot.node('Teacher', 'Teacher')
dot.node('Administrator', 'Administrator')
dot.node('GuestLecturer', 'Guest Lecturer')

# Edges to Central Node
dot.edge('School System', 'Person')
dot.edge('School System', 'Teachable')
dot.edge('School System', 'Evaluable')
dot.edge('School System', 'Course')
dot.edge('School System', 'Department')
dot.edge('School System', 'Classroom')

# Inheritance edges
dot.edge('Person', 'Student', label='Inherits')
dot.edge('Person', 'Teacher', label='Inherits')
dot.edge('Person', 'Administrator', label='Inherits')

# Interface implementations
dot.edge('Teachable', 'Teacher', label='Implements')
dot.edge('Teachable', 'GuestLecturer', label='Implements')
dot.edge('Evaluable', 'Student', label='Implements')
dot.edge('Evaluable', 'Teacher', label='Implements (optional)')

# Relationships
dot.edge('Course', 'Teacher', label='Assigned to')
dot.edge('Course', 'Student', label='Enrolled')
dot.edge('Department', 'Teacher', label='Head')
dot.edge('Department', 'Course', label='Contains')
dot.edge('Classroom', 'Course', label='Scheduled')

# Render the mind map
mind_map_path = '/school_system_mind_map.png'
dot.render(mind_map_path, view=False)

mind_map_path
