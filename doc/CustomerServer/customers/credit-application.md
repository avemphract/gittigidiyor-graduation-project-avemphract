# Apply for Credit

Apply to credit with given customer.

**URL** : `http://localhost:8083/api/customers/creditApplication`

**Method** : `POST`

**Request Params**

```json
{
  "name": "string",
  "phoneNumber": 0,
  "salary": 0,
  "surname": "string",
  "tcNumber": 0
}
```

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
  "approval": true,
  "customerTcNumber": 0,
  "givenCreditAmount": 0,
  "id": 1
}
```