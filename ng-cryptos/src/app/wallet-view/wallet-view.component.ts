import {Component, OnInit} from '@angular/core';
import {Wallet} from "../model/wallet";
import {PricingService} from "../pricing.service";

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {


  wallet = new Wallet()
  iniDollar = 100000;


  constructor(public pricingService: PricingService) {
    this.wallet.pricingService = pricingService;
    pricingService.loadPrices()
      .then(data => console.log('>>>>', data))
      .then(() => this.initWallet())
    ;
  }

  initWallet() {
    this.wallet.deposit(this.iniDollar);
    this.wallet.buy(1, 'BTC')
    this.wallet.sell(1, 'XRP')

  }

  ngOnInit() {


  }

  deposit(value: string) {
    let money = (parseFloat(value));
    if (money > 0) {
      this.wallet.deposit(money)
    }
  }


  letsBuy(quantity: string, symbol: string) {
    this.wallet.buy(parseFloat(quantity), symbol);
    console.log(this.wallet.lines);


  }

  letsSell(quantity: string, symbol: string) {
    this.wallet.sell(parseFloat(quantity), symbol);
    console.log(this.wallet.lines);


  }

  getColor(symbol) {
    return this.pricingService.getColor(symbol);

  }

}
