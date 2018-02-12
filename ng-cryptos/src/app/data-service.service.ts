import {Injectable} from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Wallet} from "./model/wallet";

@Injectable()
export class DataService {


  constructor(public http:HttpClient) {
  }


  fetchUsers(): Promise <User[]> {

    return this.http
      .get("http://localhost:8080/cryptos/api/users")
      .toPromise()
      .then(data => data as User[])



  }



  fetchUserWithWallets(user:User):Promise<User>{
    let url = 'http://localhost:8080/cryptos/api/users/'+user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with wallet : ' , data);
        return data as User
      })


  }




createWallet(wallet:Wallet){

  let url ="http://localhost:8080/cryptos/api/wallets";
  let dto ={ //data transfer object aux petits trucs pour jax-B
    name:wallet.name,
    user:wallet.user
  }

console.log('Sending wallet : ',  dto);
  return this.http.post(url, dto)
    .toPromise()
    .then(data => console.log('success :)',data))
    //.catch(e => console.error(' fail:(', e))
  }
  createUser(user:User){

    let url ="http://localhost:8080/cryptos/api/users";
    let dto ={ //data transfer object aux petits trucs pour jax-B
      name:user.name,

    }

    console.log('Sending users : ',  dto);
    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('success :)',data))
    //.catch(e => console.error(' fail:(', e))
  }
}
