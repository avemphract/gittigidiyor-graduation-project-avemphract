# Find All Credit Scores

Returns all saved people.

**URL** : `http://localhost:8082/api/creditScores`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
[
  {
    "tcNumber": 2,
    "creditScore": 100
  },
  {
    "tcNumber": 4,
    "creditScore": 400
  }
]
```