package www.cput.za.exampreparation.domain;
import java.io.Serializable;

/**
 * Created by Game330 on 2016-04-12.
 */
public class TreatmentSupplier  implements Serializable{

    private Long supplierCode;
    private String supplierName;
    private String treatmentName;

    public TreatmentSupplier(){

    }

     public TreatmentSupplier (Builder build) {
            this.supplierCode = build.supplierCode;
            this.supplierName = build.supplierName;
            this.treatmentName = build.treatmentName;
        }

        public Long getSupplierCode() {
            return supplierCode;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public String getTreetmentName() {
            return treatmentName;
        }

        public static class Builder{
            Long supplierCode;
            String supplierName;
            String treatmentName;

            public Builder(){

            }
            public Builder code(Long code)
            {
                this.supplierCode = code;
                return this;
            }

            public Builder supplierName(String name) {
                this.supplierName = name;
                return this;
            }

            public Builder treatmentName(String tName) {
                this.treatmentName = tName;
                return this;
            }
            public Builder copy(TreatmentSupplier supplier) {
                 supplierCode = supplier.getSupplierCode();
                 supplierName= supplier.getSupplierName();
                treatmentName= supplier.getTreetmentName();
                return this;
            }
            public TreatmentSupplier build()
            {
                return new TreatmentSupplier(this);
            }
        }
}

