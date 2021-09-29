# Find Credit Approval By Tc

Returns credit approval list result by tc

**URL** : `http://localhost:8083/api/creditApprovals/tc/:tc`

**URL Parameters** : `tc=[long]`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
[
  {
    "approval": true,
    "customerTcNumber": 0,
    "givenCreditAmount": 0,
    "id": 0
  },
  {
    "approval": true,
    "customerTcNumber": 0,
    "givenCreditAmount": 0,
    "id": 1
  }
]
```