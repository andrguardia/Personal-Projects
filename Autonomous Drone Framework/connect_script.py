import dronekit_sitl
import dronekit

sitl = dronekit_sitl.start_default()
connection_string = sitl.connection_string()

# Import DroneKit-Python
from dronekit import connect, VehicleMode

# Connect to the Vehicle.
print("Connecting to vehicle on: %s" % (connection_string,))
vehicle = connect(connection_string, wait_ready=True)
