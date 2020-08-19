package strzelecki.karol.masprojekt.model.Tyre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CarTyres extends TyreSet{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @NotBlank
    private String rollingResistance;
    @NotBlank
    private String wetGrip;
    private int noiseLevel;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    @NotNull
    private VehicleType vehicleType;



    public CarTyres() {
    }

    public CarTyres(String brand, String model, String rollingResistance, String wetGrip, int noiseLevel, Season season, VehicleType vehicleType) {
        super(brand, model);
        setRollingResistance(rollingResistance);
        setNoiseLevel(noiseLevel);
        setWetGrip(wetGrip);
        setSeason(season);
        setVehicleType(vehicleType);

    }

//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getRollingResistance() {
        return rollingResistance;
    }

    public void setRollingResistance(String rollingResistance) {
        if(!rollingResistance.matches("[a-gA-G]")){
            throw new IllegalArgumentException("Rolling Resitance are classes A-G!");
        }

        this.rollingResistance = rollingResistance;
    }

    public String getWetGrip() {
        return wetGrip;
    }

    public void setWetGrip(String wetGrip) {
        if(!wetGrip.matches("[a-gA-G]")){
            throw new IllegalArgumentException("WetGrip are classes A-G!");
        }

        this.wetGrip = wetGrip;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(int noiseLevel) {

        if (noiseLevel > 80 || noiseLevel < 50){
            throw new IllegalArgumentException("Noise level must be between 50 and 80");
        }


        this.noiseLevel = noiseLevel;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


}
