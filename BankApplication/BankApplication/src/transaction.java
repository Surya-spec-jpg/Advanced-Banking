import java.math.BigDecimal;
import java.sql.Date;

public class transaction{

    private int accno;

    private Date date;

    private String type;

    private BigDecimal amount;

    private BigDecimal balance;

    public void setAccno(int accno){

        this.accno=accno;
    }
    public int getAccno(){

        return this.accno;
    }

    public void setDate(Date date){

        this.date=date;
    }
    public Date getDate(){

        return this.date;
    }

    public void setType(String type){

        this.type=type;
    }
    public String getType(){

        return this.type;
    }

    public void setAmount(BigDecimal amount){

        this.amount=amount;
    }
    public BigDecimal getAmount(){

        return this.amount;
    }

    public void setBalance(BigDecimal balance){

        this.balance=balance;
    }
    public BigDecimal getBalance(){

        return this.balance;
    }
}
