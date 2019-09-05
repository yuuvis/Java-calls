# Core Capabilities (CRUD)

## Get the applied tenant schema

The **"Get the applied tenant schema"** endpoint returns the applied schema of the tenant (JSON / XML) belonging to you as the logged on user. The applied schema is the tenant schema, enhanced by the global schema and the system properties.


[Learn about yuuvis® API schemas](https://yuuvis.io/how-to/schema)

## Request URL

https://api.yuuvis.io/dms/schema/native

## Request headers

```
Ocp-Apim-Subscription-Key           string          Subscription key which provides access to this API. Found in your Profile.
```

## Request body

## Responses

## 200 OK

### OK


## Representations

`application/json`

```
Sample

{}

Schema

{
  "type": "object"
}
```

`application/xml`

```
Sample

{
  "type": "object"
}

Schema

 {
  "type": "object"
}

```

## 403 Forbidden

### Forbidden

### Code samples

[Get applied Tenant Schema](./Get-Tenant-Schema-Applied.html)
