package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by aa on 2016/1/4.
 */
@Entity
@Table(name = "City1", schema = "")
@SuppressWarnings("serial")
public class CityEntity implements java.io.Serializable {
    private java.lang.String id;
    private java.lang.String provinceID;
    private java.lang.String cityCode;
    private java.lang.String cityAresCode;
    private java.lang.String cityName;
    private java.lang.String provinceCode;
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "PROVINCEID", nullable = false, length = 36)
    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }
    @Column(name = "CITYCODE", nullable = false, length = 10)
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    @Column(name = "CITYARESCODE", nullable = false, length = 10)
    public String getCityAresCode() {
        return cityAresCode;
    }

    public void setCityAresCode(String cityAresCode) {
        this.cityAresCode = cityAresCode;
    }
    @Column(name = "CITYNAME", nullable = false, length = 10)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    @Column(name = "PROVINCECODE", nullable = false, length = 10)
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
