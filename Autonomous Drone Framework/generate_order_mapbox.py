import boto3
import random

def payload_weight(weight):
    #This function converts input payload weight values in kg to str values
    weight = str(weight)
    return weight

def payload_id():
    #This function assigns a unique number in the range from 1 to 100 as the payload id. Output is a string.
    return str(random.randint(0,100))

def compile(lat1,long1, lat2, long2, weight, id):
    #This function compiles SQS message strings
    #compiles message has the following structure: [latitude1,longitude1,latitude2,longitude2,weight,payload id]
    separator = ';'
    w = payload_weight(weight)
    msg = lat1 + separator + long1 + separator + lat2 + separator + long2 + separator + w + separator + id
    return msg

def create_queue(lat1,long1, lat2, long2, weight, Author):
    #Creating an SQS Queue
    #Each Message will be comprised of 4 key data points in the form of latitude1;longitude1;latitude2;longitude2;payload_weight;payload_id
    id = payload_id()
    message = compile(lat1,long1, lat2, long2, weight, id)
    sqs = boto3.resource('sqs')
    queue = sqs.create_queue(QueueName=id, Attributes={'DelaySeconds': '5'})
    response = queue.send_message(MessageBody=message, MessageAttributes={'Author': {'StringValue': Author,'DataType': 'String'}})
    return print('Queue Succesfully Created!')

