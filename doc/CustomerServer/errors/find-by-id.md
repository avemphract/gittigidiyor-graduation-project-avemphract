# Find Error By Id

Returns an error by id.

**URL** : `http://localhost:8083/api/errors/:id`

**URL Parameters** : `id=[long]`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
  "createDate": "2021-09-29T15:53:15.761Z",
  "errorCode": 400,
  "errorMessage": "Tc number must be 11 length. Actual: 0",
  "errorUrl": "http://localhost:8083/api/customers/creditApplication"
}
```