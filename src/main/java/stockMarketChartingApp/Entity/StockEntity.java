package stockMarketChartingApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="stocks")
public class StockEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Stock name is mandatory")
    @Size(max = 100, message = "Stock name cannot exceed 100 characters")
    private String name;
	
    @NotNull(message = "Stock price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Stock price must be greater than 0")
    private Double price;
    
	/*
	 * @NotBlank(message = "Exchange is mandatory")
	 * 
	 * @Size(max = 50, message = "Exchange name cannot exceed 50 characters")
	 * private String exchange;
	 */
    @NotBlank(message = "Symbol is mandatory")
    @Size(max = 10, message = "Symbol cannot exceed 10 characters")
    private String symbol; 

    public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	/*
	 * public String getExchange() { return exchange; }
	 * 
	 * public void setExchange(String exchange) { this.exchange = exchange; }
	 */

	public StockEntity(Long id, String name, Double price,  String symbol) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		/* this.exchange = exchange; */
		this.symbol = symbol;
	}

	public StockEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", name=" + name + ", price=" + price + ",  symbol="
				+ symbol + "]";
	}


}