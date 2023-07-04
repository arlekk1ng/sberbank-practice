import logo from './logo.svg';
import './App.css';
import {ProductCreation} from "./components/products/ProductCreation";
import {ProductChange} from "./components/products/ProductChange";
import {ProductRemoval} from "./components/products/ProductRemoval";
import {ClientSearch} from "./components/clients/ClientSearch";
import {Payment} from "./components/payments/Payment";

function App() {
    return (
        <div>
            <h2>Создание продукта</h2>
            <ProductCreation/>

            <h2>Изменение продукта</h2>
            <ProductChange/>

            <h2>Удаление продукта</h2>
            <ProductRemoval/>

            <h2>Отображение для пользователя</h2>
            <ClientSearch/>

            <h2>Оплата корзины</h2>
            <Payment/>
        </div>
    )
}

export default App;
