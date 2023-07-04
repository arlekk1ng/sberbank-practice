const client =
{
    "id": 1,
    "name": "Илья",
    "cart": {
        "promoCode": "1xBet",
        "cartProductList": [
            {
                "product": {
                    "id": 3,
                    "name": "Яблоко",
                    "price": 50.00
                },
                "productCount": 2
            },
            {
                "product": {
                    "id": 2,
                    "name": "Груша",
                    "price": 100.00
                },
                "productCount": 1
            }
        ]
    }
}

export const ClientSearch = () => {
    const getClient = () => {}
    const changeProductCountInCart = () => {}
    const deleteProductInCart = () => {}
    const addProductInCart = () => {}

    const CartProductList = () => {
        return (
            client.cart.cartProductList.map(cartProduct => {
                return (
                    <div className={"row"}>
                        <div className={"column-cart-product"}>{cartProduct.product.id}</div>
                        <div className={"column-cart-product"}>{cartProduct.product.name}</div>
                        <div className={"column-cart-product"}>{cartProduct.product.price}</div>
                        <div className={"column-cart-product"}>
                            <input type={"number"} min={1} defaultValue={cartProduct.productCount}/>
                            <button onClick={changeProductCountInCart}>Изменить</button>
                        </div>
                        <div className={"column-cart-product"}>
                            <button onClick={deleteProductInCart}>Удалить</button>
                        </div>
                    </div>
                )
            })
        )
    }

    return (
        <div>
            <div className={"client-container"}>
                <div className={"row"}>
                    <div className={"column-client-field-name"}>id:</div>
                    <div className={"column-client-field-value"}>
                        <input type={"number"} min={1} defaultValue={1}/>
                        <button onClick={getClient}>Найти</button>
                    </div>
                </div>
                <div className={"row"}>
                    <div className={"column-client-field-name"}>Имя:</div>
                    <div className={"column-client-field-value"}>{client.name}</div>
                </div>
            </div>

            <div className={"cart-container"}>
                <div><b>Корзина</b></div>
                <div className={"row"}>
                    <div className={"column-client-field-name"}>Промокод:</div>
                    <div className={"column-client-field-value"}>{client.cart.promoCode}</div>
                </div>

                <div>Продукты:</div>
                <div className={"row row-cart-product-header"}>
                    <div className={"column-cart-product"}>id</div>
                    <div className={"column-cart-product"}>Наименование</div>
                    <div className={"column-cart-product"}>Цена</div>
                    <div className={"column-cart-product"}>Количество</div>
                </div>
                <CartProductList/>
                <div className={"row"}>
                    <div className={"column-cart-product"}>
                        <input type={"number"} min={1} defaultValue={1}/>
                        <button onClick={addProductInCart}>Добавить</button>
                    </div>
                </div>
            </div>
        </div>
    )
}
