import java.math.BigDecimal;
import java.sql.Date;

public class accountMaster{

    private int accno;

    private String name;

    private String address;

    private Date date;

    private String pan;

    private String phone;

    private String email;

    private BigDecimal balance;

    public accountMaster(){

    }
    public void setAccno(int accno){

        this.accno=accno;
    }
    public int getAccno(){

        return this.accno;
    }

    public void setName(String name){

        this.name=name;
    }
    public String getName(){

        return this.name;
    }

    public void setAddress(String address){

        this.address=address;
    }
    public String getAddress(){

        return this.address;
    }

    public void setDate(Date date){

        this.date=date;
    }
    public Date getDate(){

        return this.date;
    }

    public void setPan(String pan){

        this.pan=pan;
    }
    public String getPan(){

        return this.pan;
    }

    public void setPhone(String phone){

        this.phone=phone;
    }
    public String getPhone(){

        return this.phone;
    }

    public void setEmail(String email){

        this.email=email;
    }
    public String getEmail(){

        return this.email;
    }

    public void setBalance(BigDecimal balance){

        this.balance=balance;
    }
    public BigDecimal getBalance(){

        return this.balance;
    }
}

