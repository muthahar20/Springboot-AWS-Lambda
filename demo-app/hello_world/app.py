import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('demo-app-tbl')

def lambda_handler(event, context):
    response = table.get_item(
        Key = {
            'ID': '1'
        }
    )
    
    # Check if 'Item' exists in the response
    if 'Item' in response:
        visit_count = response['Item']['counter']
        visit_count = str(int(visit_count) + 1)
    else:
        visit_count = '1'  # Initialize visit count if item doesn't exist

    # Update or create the item with the new visit count
    table.put_item(
        Item = {
            'ID': '1',
            'counter': visit_count
        }
    )

    return {
        'statusCode': 200,
        'headers': {
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': '*'
        },
        'body': visit_count
    }
