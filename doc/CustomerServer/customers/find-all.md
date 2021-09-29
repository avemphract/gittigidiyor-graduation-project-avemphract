# Find All Customers

Returns all customers.

**URL** : `http://localhost:8083/api/customers`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
[
  {
    "creditApprovalsId": [
      1
    ],
    "name": "string",
    "phoneNumber": 0,
    "salary": 0,
    "surname": "string",
    "tcNumber": 0
  },
  {
    "creditApprovalsId": [
      0
    ],
    "name": "string",
    "phoneNumber": 0,
    "salary": 0,
    "surname": "string",
    "tcNumber": 0
  }
]
```