import { NgModule } from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {DictionaryPageComponent} from "./dictionary-page/dictionary-page.component";
import {DictionaryDetailComponent} from "./dictionary-detail/dictionary-detail.component";
import {AuthGuard} from "./auth.guard";


const routes: Routes = [{
  path: 'dictionary',
  component: DictionaryPageComponent,
  children:[
    {
      path: ':key',
      component: DictionaryDetailComponent,
      canActivate: [AuthGuard]
    }
  ]

}];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    preloadingStrategy: PreloadAllModules
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
