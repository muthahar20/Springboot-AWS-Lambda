import boto3
import json

def lambda_handler(event, context):
    # Initialize DynamoDB client
    dynamodb = boto3.client('dynamodb')
    
    # Define the table name
    table_name = 'products'
    
    # Retrieve data from DynamoDB
    try:
        response = dynamodb.scan(TableName=table_name)
        items = response['Items']
        
        # Convert DynamoDB JSON format to regular JSON
        formatted_items = [json.loads(item) for item in items]
        
        return {
            'statusCode': 200,
            'body': json.dumps(formatted_items)
        }
    except Exception as e:
        return {
            'statusCode': 500,
            'body': str(e)
        }