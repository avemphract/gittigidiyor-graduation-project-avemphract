# Find Credit Scores By Tc Number

Returns a person by Tc number

**URL** : `http://localhost:8082/api/creditScores/tc/:tc`

**URL Parameters** : `tc=[long]`

**Method** : `GET`

**Request Params**

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
  "tcNumber": 2,
  "creditScore": 100
}
```