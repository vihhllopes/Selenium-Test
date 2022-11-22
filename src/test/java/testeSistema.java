import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


@TestInstance(Lifecycle.PER_CLASS)
public class testeSistema {

  WebDriver webdriver;

  @BeforeAll
  public void setupAll(){
    System.setProperty("webdriver.chrome.driver",
            "src/test/resources/chromedriver.exe");
  }

  @BeforeEach
  public void setup(){
    webdriver = new ChromeDriver();
    webdriver.manage().window().maximize();

  }

  @AfterEach
  public void closeDriver(){
    webdriver.close();
  }

// PAGINA DA IMPERIO

  @Test
  public void openNewPage(){
    webdriver.get("https://www.lojasimperio.com.br/");
    Assertions.assertEquals("https://www.lojasimperio.com.br/",
            webdriver.getCurrentUrl());
  }

  //clicando em um componente
  //(CLICK)
  @Test
  public void clickPage(){
    webdriver.get("https://www.lojasimperio.com.br/");
    Actions actions = new Actions(webdriver);
    WebElement botao = webdriver.findElement(
            By.xpath("//*[@id=\"__next\"]/header/div[2]/div/a[1]"));
    botao.click();
    actions.moveToElement(botao).perform();
    Assertions.assertTrue(botao.isEnabled());
  }

  //Listando 2 divs
  //(findElements)
  @Test
  public void listarElementosTest(){
    webdriver.get("https://www.lojasimperio.com.br/");
    WebElement lista = webdriver.findElement(By.xpath("//*[@id=\"__next\"]/main/section[2]/div[1]/div[1]"));
    List<WebElement> elements = lista.findElements(By.xpath("./div"));
    Assertions.assertEquals(2, elements.size());

  }

  //Procurar tv no pesquisar e submeter
  // (sedKeys e submit)
  @Test
  public void procurarTv (){
    webdriver.get("https://www.lojasimperio.com.br/");
    Actions actions = new Actions(webdriver);
    WebElement search = webdriver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[1]/div[2]/div/form/div/input"));
    search.sendKeys("televisão");
    search.submit();
    actions.moveToElement(search).perform();
    Assertions.assertTrue(search.isEnabled());
  }



  //Ao passar o mouse no zoom do produto da imperio, aparecer a div ao lado com a imagem
  //(moveToElement)
  @Test
  public void moveBotaoZoom(){
    webdriver.get("https://www.lojasimperio.com.br/produto/tv-led-smart-50-samsung-crystal-uhd-4k-un50bu8000gxzd-design-sem-limites-wi-fi-bluetooth-3-hdmi-2-077010260.html");
    Actions actions = new Actions(webdriver);
    WebElement zoom = webdriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div/div[1]/div/div[2]/div/div/img"));
    actions.moveToElement(zoom).perform();
    Assertions.assertTrue(zoom.isEnabled());
  }

  //Botão de Ofertas
  //(ByID)
  @Test
  public void openOfertasTrue(){
    webdriver.get("https://www.lojasimperio.com.br/");
    Actions actions = new Actions(webdriver);
    WebElement ofertas = webdriver.findElement(By.id("offers"));
    ofertas.click();
    actions.moveToElement(ofertas).perform();
    Assertions.assertTrue(ofertas.isEnabled());

  }



  //Teste da Falso porque o botão ele vai para link diferente
  //FALSE
  @Test
  public void openCheckoutPageFalse(){
    webdriver.get("https://www.lojasimperio.com.br/produto/ar-condicionado-de-janela-springer-midea-mecanico-qci075bb-7500-btus-220v-066040013.html");
    WebElement favorite = webdriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div/div[1]/section/div[3]/button[1]"));
    favorite.click();
    Assertions.assertFalse(favorite.isEnabled(),"https://www.lojasimperio.com.br/");

  }

  // PAGINA UOL
  //Teste no site Uol para aparecer a frase "UOL - Seu universo online" quando o mouse ficar por cima da Logo
  //(moveToElement)
  @Test
  public void tooltipTest(){
    webdriver.get("https://www.uol.com.br/");
    Actions actions = new Actions(webdriver);
    WebElement uol = webdriver.findElement(By.xpath("//*[@id=\"app\"]/div/header/h1/a"));
    Assertions.assertEquals("UOL - Seu universo online", uol.getAttribute("title"));
    actions.moveToElement(uol).perform();
    Assertions.assertTrue(uol.isEnabled());
  }

  // PAGINA COTRIP
  //Testes com Select das Paginas Destino e Cadastro
  //(selectBYIndex)
  @Test
  public void selectCotripCadastroTest(){
    webdriver.get("https://cotrip-1-0.vercel.app/Destino");
    WebElement botaoSelect = webdriver.findElement(By.xpath("/html/body/div/div/div/div/form/select"));
    Select select = new Select(botaoSelect);
    select.selectByIndex(0);
    Assertions.assertTrue(botaoSelect.isEnabled());

  }

  @Test
  public void selectCotripDestinoTest(){
    webdriver.get("https://cotrip-1-0.vercel.app/Cadastro");
    WebElement botaoSelect = webdriver.findElement(By.xpath("/html/body/div/div/main/form/fieldset[2]/select[1]"));
    Select select = new Select(botaoSelect);
    select.selectByIndex(1);
    Assertions.assertTrue(botaoSelect.isEnabled());

  }
}