import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { CollectionComponent } from './collection/collection.component';
import { EditCollectionComponent } from './edit-collection/edit-collection.component';
import { EditItemComponent } from './edit-item/edit-item.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { HomeComponent } from './home/home.component';
import { ItemComponent } from './item/item.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
{ path: 'home', component: HomeComponent },
{ path: 'login', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'profile', component: ProfileComponent },
{ path: 'user', component: BoardUserComponent },
{ path: 'mod', component: BoardModeratorComponent },
{ path: 'admin', component: BoardAdminComponent },
{ path: 'admin/add-user', component: AddUserComponent },
{ path: 'admin/edit-user', component: EditUserComponent },
{ path: '', redirectTo: 'home', pathMatch: 'full' },
{ path: 'user/collection-page', component: CollectionComponent },
{ path: 'user/edit-collection', component: EditCollectionComponent },
{ path: 'item/edit-item', component: EditItemComponent },
{ path: 'item', component: ItemComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
