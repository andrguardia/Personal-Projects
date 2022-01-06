import boto3
import time
#Processing a Message
#Get server Resource
sqs_resource = boto3.resource('sqs')
sqs_client = boto3.client('sqs')
#Get the Queue
queue = sqs_resource.get_queue_by_name(QueueName='Delivery_Line')
def read_message():
    #Split Message Body and store in variables
    message = sqs_client.receive_message(
    QueueUrl='https://sqs.us-east-2.amazonaws.com/548002151864/Delivery_Line',
)

    message1 = message.get("Messages")
    message1 = message1[0]
    message_body = message1.get("Body")
    receipt_handle = message1.get("ReceiptHandle")
    mess = message_body.split(';')
    starting_latitude = mess[0]
    starting_longitude = mess[1]
    final_latitude = mess[2]
    final_longitude = mess[3]
    payload_weight = mess[4]
    payload_id = mess[5]
    #Print string so user knows what's going on

    print('---Reading Queue Message---')
    print('Starting Latitude:', mess[0])
    print('Starting Longitude:', mess[1])
    print('Final Latitude:', mess[2])
    print('Final Longitude:', mess[3])
    print('Payload Weight:', mess[4], 'lbs')
    print('Payload ID:', mess[5])
    message = sqs_client.delete_message(
    QueueUrl='https://sqs.us-east-2.amazonaws.com/548002151864/Delivery_Line',
    ReceiptHandle=receipt_handle
    )

    print('Message Read!')
rep = 1 #Initialize response, value will be updated once loop starts
def remaining_messages():
    response = sqs_client.get_queue_attributes(
    QueueUrl='https://sqs.us-east-2.amazonaws.com/548002151864/Delivery_Line',
    AttributeNames=[
        'ApproximateNumberOfMessages'
        ]
    )
    response = response.get("Attributes")
    rep = int(response.get("ApproximateNumberOfMessages"))
    return rep
response_init = remaining_messages()
for rep in range(0,response_init):
    rep = remaining_messages()
    read_message()
    time.sleep(1)
