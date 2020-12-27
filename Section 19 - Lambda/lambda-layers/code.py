import numpy as np
from scipy.spatial import ConvexHull

def lambda_handler(event, context):

print("\nUsing NumPy\n")

print("random matrix_a =")
matrix_a = np.random.randint(10, size=(4, 4))
print(matrix_a)

print("random matrix_b =")
matrix_b = np.random.randint(10, size=(4, 4))
print(matrix_b)

print("matrix_a * matrix_b = ")
print(matrix_a.dot(matrix_b))
print("\nUsing SciPy\n")

num_points = 10
print(num_points, "random points:")
points = np.random.rand(num_points, 2)
for i, point in enumerate(points):
print(i, '->', point)

hull = ConvexHull(points)
print("The smallest convex set containing all",
    num_points, "points has", len(hull.simplices),
    "sides,\nconnecting points:")
for simplex in hull.simplices:
print(simplex[0], '<->', simplex[1])