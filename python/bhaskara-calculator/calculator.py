import math

# Simple Bhaskara Calculator

# Step 1: get coefficients a, b, c
print("- Bhaskara Calculator -\n")
a = float(input("> Input the first value (coefficient a): "))
b = float(input("> Input the second value (coefficient b): "))
c = float(input("> Input the third value (coefficient c): "))

# Step 2: calculate discriminant
delta = b**2 - 4*a*c

# Step 3: determine roots based on delta
if a == 0:
    print("> Coefficient 'a' cannot be zero for a quadratic equation.")
elif delta >= 0:
    x1 = (-b + math.sqrt(delta)) / (2*a)
    x2 = (-b - math.sqrt(delta)) / (2*a)
    print(f"> Found roots: {x1} and {x2}")
else:
    print("> This equation has no real roots.")