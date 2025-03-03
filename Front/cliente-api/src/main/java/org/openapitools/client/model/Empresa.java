/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * Empresa
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-03-03T02:20:28.516136200+01:00[Europe/Madrid]", comments = "Generator version: 7.11.0")
public class Empresa {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Integer id;

  public static final String SERIALIZED_NAME_NOMBRE_EMPRESA = "nombreEmpresa";
  @SerializedName(SERIALIZED_NAME_NOMBRE_EMPRESA)
  @javax.annotation.Nullable
  private String nombreEmpresa;

  public static final String SERIALIZED_NAME_TUTOR_LABORAL_NOMBRE = "tutorLaboralNombre";
  @SerializedName(SERIALIZED_NAME_TUTOR_LABORAL_NOMBRE)
  @javax.annotation.Nullable
  private String tutorLaboralNombre;

  public static final String SERIALIZED_NAME_TUTOR_LABORAL_EMAIL = "tutorLaboralEmail";
  @SerializedName(SERIALIZED_NAME_TUTOR_LABORAL_EMAIL)
  @javax.annotation.Nullable
  private String tutorLaboralEmail;

  public static final String SERIALIZED_NAME_TUTOR_LABORAL_TELEFONO = "tutorLaboralTelefono";
  @SerializedName(SERIALIZED_NAME_TUTOR_LABORAL_TELEFONO)
  @javax.annotation.Nullable
  private String tutorLaboralTelefono;

  public static final String SERIALIZED_NAME_ACTIVO = "activo";
  @SerializedName(SERIALIZED_NAME_ACTIVO)
  @javax.annotation.Nullable
  private Boolean activo;

  public Empresa() {
  }

  public Empresa id(@javax.annotation.Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @javax.annotation.Nullable
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Integer id) {
    this.id = id;
  }


  public Empresa nombreEmpresa(@javax.annotation.Nullable String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
    return this;
  }

  /**
   * Get nombreEmpresa
   * @return nombreEmpresa
   */
  @javax.annotation.Nullable
  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public void setNombreEmpresa(@javax.annotation.Nullable String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }


  public Empresa tutorLaboralNombre(@javax.annotation.Nullable String tutorLaboralNombre) {
    this.tutorLaboralNombre = tutorLaboralNombre;
    return this;
  }

  /**
   * Get tutorLaboralNombre
   * @return tutorLaboralNombre
   */
  @javax.annotation.Nullable
  public String getTutorLaboralNombre() {
    return tutorLaboralNombre;
  }

  public void setTutorLaboralNombre(@javax.annotation.Nullable String tutorLaboralNombre) {
    this.tutorLaboralNombre = tutorLaboralNombre;
  }


  public Empresa tutorLaboralEmail(@javax.annotation.Nullable String tutorLaboralEmail) {
    this.tutorLaboralEmail = tutorLaboralEmail;
    return this;
  }

  /**
   * Get tutorLaboralEmail
   * @return tutorLaboralEmail
   */
  @javax.annotation.Nullable
  public String getTutorLaboralEmail() {
    return tutorLaboralEmail;
  }

  public void setTutorLaboralEmail(@javax.annotation.Nullable String tutorLaboralEmail) {
    this.tutorLaboralEmail = tutorLaboralEmail;
  }


  public Empresa tutorLaboralTelefono(@javax.annotation.Nullable String tutorLaboralTelefono) {
    this.tutorLaboralTelefono = tutorLaboralTelefono;
    return this;
  }

  /**
   * Get tutorLaboralTelefono
   * @return tutorLaboralTelefono
   */
  @javax.annotation.Nullable
  public String getTutorLaboralTelefono() {
    return tutorLaboralTelefono;
  }

  public void setTutorLaboralTelefono(@javax.annotation.Nullable String tutorLaboralTelefono) {
    this.tutorLaboralTelefono = tutorLaboralTelefono;
  }


  public Empresa activo(@javax.annotation.Nullable Boolean activo) {
    this.activo = activo;
    return this;
  }

  /**
   * Get activo
   * @return activo
   */
  @javax.annotation.Nullable
  public Boolean getActivo() {
    return activo;
  }

  public void setActivo(@javax.annotation.Nullable Boolean activo) {
    this.activo = activo;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Empresa empresa = (Empresa) o;
    return Objects.equals(this.id, empresa.id) &&
        Objects.equals(this.nombreEmpresa, empresa.nombreEmpresa) &&
        Objects.equals(this.tutorLaboralNombre, empresa.tutorLaboralNombre) &&
        Objects.equals(this.tutorLaboralEmail, empresa.tutorLaboralEmail) &&
        Objects.equals(this.tutorLaboralTelefono, empresa.tutorLaboralTelefono) &&
        Objects.equals(this.activo, empresa.activo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombreEmpresa, tutorLaboralNombre, tutorLaboralEmail, tutorLaboralTelefono, activo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Empresa {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombreEmpresa: ").append(toIndentedString(nombreEmpresa)).append("\n");
    sb.append("    tutorLaboralNombre: ").append(toIndentedString(tutorLaboralNombre)).append("\n");
    sb.append("    tutorLaboralEmail: ").append(toIndentedString(tutorLaboralEmail)).append("\n");
    sb.append("    tutorLaboralTelefono: ").append(toIndentedString(tutorLaboralTelefono)).append("\n");
    sb.append("    activo: ").append(toIndentedString(activo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("id");
    openapiFields.add("nombreEmpresa");
    openapiFields.add("tutorLaboralNombre");
    openapiFields.add("tutorLaboralEmail");
    openapiFields.add("tutorLaboralTelefono");
    openapiFields.add("activo");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Empresa
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Empresa.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Empresa is not found in the empty JSON string", Empresa.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Empresa.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Empresa` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("nombreEmpresa") != null && !jsonObj.get("nombreEmpresa").isJsonNull()) && !jsonObj.get("nombreEmpresa").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `nombreEmpresa` to be a primitive type in the JSON string but got `%s`", jsonObj.get("nombreEmpresa").toString()));
      }
      if ((jsonObj.get("tutorLaboralNombre") != null && !jsonObj.get("tutorLaboralNombre").isJsonNull()) && !jsonObj.get("tutorLaboralNombre").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `tutorLaboralNombre` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tutorLaboralNombre").toString()));
      }
      if ((jsonObj.get("tutorLaboralEmail") != null && !jsonObj.get("tutorLaboralEmail").isJsonNull()) && !jsonObj.get("tutorLaboralEmail").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `tutorLaboralEmail` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tutorLaboralEmail").toString()));
      }
      if ((jsonObj.get("tutorLaboralTelefono") != null && !jsonObj.get("tutorLaboralTelefono").isJsonNull()) && !jsonObj.get("tutorLaboralTelefono").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `tutorLaboralTelefono` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tutorLaboralTelefono").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Empresa.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Empresa' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Empresa> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Empresa.class));

       return (TypeAdapter<T>) new TypeAdapter<Empresa>() {
           @Override
           public void write(JsonWriter out, Empresa value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Empresa read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Empresa given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Empresa
   * @throws IOException if the JSON string is invalid with respect to Empresa
   */
  public static Empresa fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Empresa.class);
  }

  /**
   * Convert an instance of Empresa to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

