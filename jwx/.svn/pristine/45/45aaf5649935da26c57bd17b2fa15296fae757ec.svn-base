package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by aa on 2016/1/3.
 */
@Entity
@Table(name = "Province1", schema = "")
@SuppressWarnings("serial")
public class ProvinceEntity implements java.io.Serializable {
    private java.lang.String provinceID;
    private java.lang.String provinceName;
    private java.lang.String provinceCode;
    private java.lang.String provinceAresCode;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    @Column(name = "PROVINCENAME", nullable = false, length = 36)
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Column(name = "PROVINCECODE", nullable = false, length = 36)
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Column(name = "PROVINCEARESCODE", nullable = false, length = 36)
    public String getProvinceAresCode() {
        return provinceAresCode;
    }

    public void setProvinceAresCode(String provinceAresCode) {
        this.provinceAresCode = provinceAresCode;
    }
}
