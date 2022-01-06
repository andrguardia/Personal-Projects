import boto3
import random
from geopy.geocoders import Nominatim
#Define Starting Location
starting_locator = Nominatim(user_agent='myGeocoder')
starting_location = starting_locator.geocode('3333 South State Street, Chicago, IL 60616, USA')
starting_latitude = starting_location.latitude
starting_longitude = starting_location.longitude
#Converting Integer to String
starting_latitude = str(starting_latitude)
starting_longitude = str(starting_longitude)
#Print Latitude and Longitude Values
print('Starting Latitude =', starting_latitude)
print('Starting Longitude =', starting_longitude)
#Define Desired Destination
final_locator = Nominatim(user_agent='myGeocoder')
final_location = final_locator.geocode('201 E Randolph St, Chicago, IL 60602, USA')
final_latitude = final_location.latitude
final_longitude = final_location.longitude
#Converting Integer to String
final_latitude = str(final_latitude)
final_longitude = str(final_longitude)
#Print Latitude and Longitude Values
print('Final Latitude =', final_latitude)
print('Final Longitude =', final_longitude)
#Define Payload Data
payload_weight = str(2) #in kg
payload_id = str(random.randint(0,100)) #Defines payload id number

#Appending String
separator = ';'

location_data = starting_latitude + separator + starting_longitude + separator + final_latitude + separator + final_longitude + separator + payload_weight + separator + payload_id
print(location_data)
#Creating an SQS Queue
#Each Message will be comprised of 4 key data points in the form of starting_latitude;starting_longitude;final_latitude;final_longitude;payload_weight;payload_id
sqs = boto3.resource('sqs')
queue = sqs.create_queue(QueueName='My_Queue', Attributes={'DelaySeconds': '5'})
#Create a new Message
response = queue.send_message(MessageBody=location_data, MessageAttributes={'Author': {'StringValue': 'Andrew','DataType': 'String'}})
#Obtain message ID and MD5
print(response.get('MessageId'))
print(response.get('MD5OfMessageBody'))
