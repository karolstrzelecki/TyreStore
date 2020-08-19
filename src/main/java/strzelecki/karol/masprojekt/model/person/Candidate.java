package strzelecki.karol.masprojekt.model.person;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull
    private LocalDate applicationDate;

    @NotBlank
    private String applicationDocuments;
    @OneToOne(mappedBy = "candidate")
    private Person person;


    public Candidate() {
    }

    public Candidate(@NotNull LocalDate applicationDate, @NotBlank String applicationDocuments) {
        this.applicationDate = applicationDate;
        this.applicationDocuments = applicationDocuments;
    }

    public void setPerson(Person person) {
        if(this.person != person) {
            if (this.person != null) {
                Person tmp = this.person;
                this.person = null;
                tmp.removeCandidateRole();

            }

            if (person != null) {
                this.person = person;
                person.addCandidateRole(this);

            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicationDocuments() {
        return applicationDocuments;
    }

    public void setApplicationDocuments(String applicationDocuments) {
        this.applicationDocuments = applicationDocuments;
    }

    public Person getPerson() {
        return person;
    }
}
