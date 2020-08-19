package strzelecki.karol.masprojekt.model.Tyre;

import sun.util.resources.cldr.gv.LocaleNames_gv;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="physicalparameters")
public class PhysicalParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Integer width;
    @NotNull
    private Integer aspectRatio;
    @NotNull
    private Integer rim;

    @OneToMany(mappedBy = "physicalParameters", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TyreSet> tyres = new HashSet<>();

    public PhysicalParameters() {
    }

    public PhysicalParameters(@NotNull Integer width, @NotNull Integer aspectRatio, @NotNull Integer rim) {
        this.width = width;
        this.aspectRatio = aspectRatio;
        this.rim = rim;
    }

    public void removeTyreSet(TyreSet tyreSet){
        if(this.tyres.contains(tyreSet)){
            this.tyres.remove(tyreSet);
            System.out.println(tyres);
            tyreSet.setPhysicalParameters(null);
        }
    }

    public void addTyres(TyreSet tyre){
        if (!this.tyres.contains(tyre)){
            this.tyres.add(tyre);
            tyre.setPhysicalParameters(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Integer getRim() {
        return rim;
    }

    public void setRim(Integer rim) {
        this.rim = rim;
    }

    @Override
    public String toString() {
        return "\nszerokośc: " + width + ", wysokosc: " + aspectRatio + ", rozmiar: " + rim;

    }



}
