# UsuarioServiceApiApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**borrarRegistro**](UsuarioServiceApiApi.md#borrarRegistro) | **DELETE** /api/users/registro/{id} | Borrar Registro |
| [**cambiarContrasea**](UsuarioServiceApiApi.md#cambiarContrasea) | **PUT** /api/users | Cambia el pasword de User |
| [**consultarDetalles**](UsuarioServiceApiApi.md#consultarDetalles) | **GET** /api/users/{idUser} | Consultar detalle user |
| [**consultarFechas**](UsuarioServiceApiApi.md#consultarFechas) | **GET** /api/users/{idUser}/registros | Consultar Fechas |
| [**crearRegistro**](UsuarioServiceApiApi.md#crearRegistro) | **POST** /api/users/registro | Crear Registro |
| [**login**](UsuarioServiceApiApi.md#login) | **POST** /api/users/login | Login de usuario |


<a id="borrarRegistro"></a>
# **borrarRegistro**
> String borrarRegistro(id)

Borrar Registro

Elimina un registro de práctica por ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioServiceApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioServiceApiApi apiInstance = new UsuarioServiceApiApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      String result = apiInstance.borrarRegistro(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioServiceApiApi#borrarRegistro");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

**String**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="cambiarContrasea"></a>
# **cambiarContrasea**
> String cambiarContrasea(changePasswordRequest)

Cambia el pasword de User

cambiael pasword

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioServiceApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioServiceApiApi apiInstance = new UsuarioServiceApiApi(defaultClient);
    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(); // ChangePasswordRequest | 
    try {
      String result = apiInstance.cambiarContrasea(changePasswordRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioServiceApiApi#cambiarContrasea");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **changePasswordRequest** | [**ChangePasswordRequest**](ChangePasswordRequest.md)|  | |

### Return type

**String**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarDetalles"></a>
# **consultarDetalles**
> List&lt;RegistroPractica&gt; consultarDetalles(idUser, fecha1, fecha2)

Consultar detalle user

Devuelve el detalle del usuario por ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioServiceApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioServiceApiApi apiInstance = new UsuarioServiceApiApi(defaultClient);
    Long idUser = 56L; // Long | 
    LocalDate fecha1 = LocalDate.now(); // LocalDate | 
    LocalDate fecha2 = LocalDate.now(); // LocalDate | 
    try {
      List<RegistroPractica> result = apiInstance.consultarDetalles(idUser, fecha1, fecha2);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioServiceApiApi#consultarDetalles");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **idUser** | **Long**|  | |
| **fecha1** | **LocalDate**|  | [optional] |
| **fecha2** | **LocalDate**|  | [optional] |

### Return type

[**List&lt;RegistroPractica&gt;**](RegistroPractica.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarFechas"></a>
# **consultarFechas**
> List&lt;FechaPractica&gt; consultarFechas(idUser, anioCurso, evaluacion, fechaInicio, fechaFin)

Consultar Fechas

Devuelve todas la fechas disponibles para el usuario

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioServiceApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioServiceApiApi apiInstance = new UsuarioServiceApiApi(defaultClient);
    Long idUser = 56L; // Long | 
    Integer anioCurso = 56; // Integer | 
    String evaluacion = "evaluacion_example"; // String | 
    LocalDate fechaInicio = LocalDate.now(); // LocalDate | 
    LocalDate fechaFin = LocalDate.now(); // LocalDate | 
    try {
      List<FechaPractica> result = apiInstance.consultarFechas(idUser, anioCurso, evaluacion, fechaInicio, fechaFin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioServiceApiApi#consultarFechas");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **idUser** | **Long**|  | |
| **anioCurso** | **Integer**|  | |
| **evaluacion** | **String**|  | |
| **fechaInicio** | **LocalDate**|  | [optional] |
| **fechaFin** | **LocalDate**|  | [optional] |

### Return type

[**List&lt;FechaPractica&gt;**](FechaPractica.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="crearRegistro"></a>
# **crearRegistro**
> String crearRegistro(registroPractica)

Crear Registro

Crea un nuevo registro de práctica.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioServiceApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioServiceApiApi apiInstance = new UsuarioServiceApiApi(defaultClient);
    RegistroPractica registroPractica = new RegistroPractica(); // RegistroPractica | 
    try {
      String result = apiInstance.crearRegistro(registroPractica);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioServiceApiApi#crearRegistro");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **registroPractica** | [**RegistroPractica**](RegistroPractica.md)|  | |

### Return type

**String**

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="login"></a>
# **login**
> UsuarioDTO login(loginRequest)

Login de usuario

Autentica al usuario y devuelve un token JWT

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioServiceApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioServiceApiApi apiInstance = new UsuarioServiceApiApi(defaultClient);
    LoginRequest loginRequest = new LoginRequest(); // LoginRequest | 
    try {
      UsuarioDTO result = apiInstance.login(loginRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioServiceApiApi#login");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **loginRequest** | [**LoginRequest**](LoginRequest.md)|  | |

### Return type

[**UsuarioDTO**](UsuarioDTO.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

