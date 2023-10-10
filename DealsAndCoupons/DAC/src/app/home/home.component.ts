import { Route, Router } from '@angular/router';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { Cart } from '../cart';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent 
{
  items:Items[] = [];
  cartObj:Cart = new Cart();
  constructor(private service:CredentialService, private router:Router)
  {

  }

  ngOnInit()
  {
    this.service.getAllItems().subscribe(data=>this.items=data);
  }
  addToCart(id:string)
  {
    if(this.service.userObj.emailId== null)
    {
      this.router.navigate(["/login"]);
    }
    else{

      this.cartObj.itemId = id;
      this.service.addItemToCart(this.cartObj).subscribe();
      
      this.router.navigate(["/cart"]);
    }

  }
  showDetails(id:any)
  {
    this.router.navigate(["/details",id]);
  }
}
