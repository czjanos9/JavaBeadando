/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

/**
 *
 * @author Jani
 */
@Entity
@Table(name = "rent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rent.findAll", query = "SELECT r FROM Rent r"),
    @NamedQuery(name = "Rent.findByRentId", query = "SELECT r FROM Rent r WHERE r.rentId = :rentId"),
    @NamedQuery(name = "Rent.findByRentDateStart", query = "SELECT r FROM Rent r WHERE r.rentDateStart = :rentDateStart"),
    @NamedQuery(name = "Rent.findByRentDateEnd", query = "SELECT r FROM Rent r WHERE r.rentDateEnd = :rentDateEnd"),
    @NamedQuery(name = "Rent.findByUserId", query = "SELECT r FROM Rent r WHERE r.userId = :userId"),
    @NamedQuery(name = "Rent.findByRoomId", query = "SELECT r FROM Rent r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Rent.findByRentStatus", query = "SELECT r FROM Rent r WHERE r.rentStatus = :rentStatus")})
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rent_id")
    private Integer rentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rent_date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentDateStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rent_date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentDateEnd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_id")
    private int roomId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rent_status")
    private int rentStatus;

    public Rent() {
    }

    public Rent(Integer rentId) {
        this.rentId = rentId;
    }

    public Rent(Integer rentId, Date rentDateStart, Date rentDateEnd, int userId, int roomId, int rentStatus) {
        this.rentId = rentId;
        this.rentDateStart = rentDateStart;
        this.rentDateEnd = rentDateEnd;
        this.userId = userId;
        this.roomId = roomId;
        this.rentStatus = rentStatus;
    }
    
    //ADDED BY MYSELF
    public static Rent getRentById(int id){
        EntityManager em = Database.getDbConn();
        return em.find(Rent.class, id);
    }
    
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("id",this.rentId);
        object.put("dateStart",this.rentDateStart);
        object.put("dateEnd",this.rentDateEnd);
        object.put("userId",this.userId);
        object.put("roomId",this.roomId);
        object.put("status",this.rentStatus);
        return object;
    } 
    //END OF ADDED BY MYSELF

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public Date getRentDateStart() {
        return rentDateStart;
    }

    public void setRentDateStart(Date rentDateStart) {
        this.rentDateStart = rentDateStart;
    }

    public Date getRentDateEnd() {
        return rentDateEnd;
    }

    public void setRentDateEnd(Date rentDateEnd) {
        this.rentDateEnd = rentDateEnd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(int rentStatus) {
        this.rentStatus = rentStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentId != null ? rentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rent)) {
            return false;
        }
        Rent other = (Rent) object;
        if ((this.rentId == null && other.rentId != null) || (this.rentId != null && !this.rentId.equals(other.rentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Rent[ rentId=" + rentId + " ]";
    }
    
}
