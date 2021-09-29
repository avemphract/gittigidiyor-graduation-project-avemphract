# Find Message By Id

Returns a message result by id

**URL** : `http://localhost:8083/api/message/:id`

**URL Parameters** : `id=[long]`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
  "context": "string",
  "id": 0,
  "toPhone": 0,
  "type": "CREDIT_APPROVAL_RESULT"
}
```