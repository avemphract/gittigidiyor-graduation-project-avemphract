# Find Credit Approval By Id

Returns a credit approval result by id

**URL** : `http://localhost:8083/api/creditApprovals/:id`

**URL Parameters** : `id=[long]`

**Method** : `GET`

**Request Params**

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