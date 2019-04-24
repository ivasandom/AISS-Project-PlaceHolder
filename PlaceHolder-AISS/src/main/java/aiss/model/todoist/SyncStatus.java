
package aiss.model.todoist;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "7d9355c5-bd28-4d39-8b8b-0b7a7682eaa2",
    "c27ee0dd-71b8-4725-af5c-3f6327bacdb4"
})
public class SyncStatus {

    @JsonProperty("7d9355c5-bd28-4d39-8b8b-0b7a7682eaa2")
    private String _7d9355c5Bd284d398b8b0b7a7682eaa2;
    @JsonProperty("c27ee0dd-71b8-4725-af5c-3f6327bacdb4")
    private String c27ee0dd71b84725Af5c3f6327bacdb4;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("7d9355c5-bd28-4d39-8b8b-0b7a7682eaa2")
    public String get7d9355c5Bd284d398b8b0b7a7682eaa2() {
        return _7d9355c5Bd284d398b8b0b7a7682eaa2;
    }

    @JsonProperty("7d9355c5-bd28-4d39-8b8b-0b7a7682eaa2")
    public void set7d9355c5Bd284d398b8b0b7a7682eaa2(String _7d9355c5Bd284d398b8b0b7a7682eaa2) {
        this._7d9355c5Bd284d398b8b0b7a7682eaa2 = _7d9355c5Bd284d398b8b0b7a7682eaa2;
    }

    @JsonProperty("c27ee0dd-71b8-4725-af5c-3f6327bacdb4")
    public String getC27ee0dd71b84725Af5c3f6327bacdb4() {
        return c27ee0dd71b84725Af5c3f6327bacdb4;
    }

    @JsonProperty("c27ee0dd-71b8-4725-af5c-3f6327bacdb4")
    public void setC27ee0dd71b84725Af5c3f6327bacdb4(String c27ee0dd71b84725Af5c3f6327bacdb4) {
        this.c27ee0dd71b84725Af5c3f6327bacdb4 = c27ee0dd71b84725Af5c3f6327bacdb4;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
