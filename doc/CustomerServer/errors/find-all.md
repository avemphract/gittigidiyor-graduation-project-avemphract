# Find All Errors

Returns all errors.

**URL** : `http://localhost:8083/api/errors`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
[
  {
    "createDate": "2021-09-29T15:53:15.761Z",
    "errorCode": 400,
    "errorClass": null,
    "errorMessage": "Tc number must be 11 length. Actual: 0",
    "errorUrl": "http://localhost:8083/api/customers/creditApplication"
  },
  {
    "createDate": "2021-09-29T16:28:38.864Z",
    "errorCode": 404,
    "errorClass": null,
    "errorMessage": "0 id has not found in creditApproval table",
    "errorUrl": "http://localhost:8083/api/creditApprovals"
  },
  {
    "createDate": "2021-09-29T16:29:27.624Z",
    "errorCode": 400,
    "errorClass": null,
    "errorMessage": "1 id is already registered in creditApproval table",
    "errorUrl": "http://localhost:8083/api/creditApprovals"
  }
]
```