import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Exchange {
    @SerializedName("result")
    private String result;
    @SerializedName("base_code")
    private String moneda;
    @SerializedName("conversion_rates")
    private Map<String, Double> convesor;

    public Exchange() {
    }

    public String getResult() {
        return result;
    }

    public String getMoneda() {
        return moneda;
    }

    public Map<String, Double> getConvesor() {
        return convesor;
    }
}
