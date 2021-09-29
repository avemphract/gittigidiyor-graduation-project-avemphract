# Delete Credit Approval

Delete to given credit approval.

**URL** : `http://localhost:8083/api/creditApprovals`

**Method** : `DELETE`

**Request Params**

```json
{
  "approval": true,
  "customerTcNumber": 0,
  "givenCreditAmount": 0,
  "id": 1
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