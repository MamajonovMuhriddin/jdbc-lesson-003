package main.java;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname")
    private String nickName;
    private String type;


    public PetEntity(Long id, String nickName, String type) {
        this.id = id;
        this.nickName = nickName;
        this.type = type;
    }

    public PetEntity(){
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickName;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickName = nickname;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetEntity)) return false;

        PetEntity petEntity = (PetEntity) o;

        if (!Objects.equals(id, petEntity.id)) return false;
        if (!Objects.equals(nickName, petEntity.nickName)) return false;
        return Objects.equals(type, petEntity.type);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nickName.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", nikename='" + nickName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
