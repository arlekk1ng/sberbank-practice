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
    const getClick = () => {}
    const changeProductCountInCart = () => {}
    const deleteProductInCart = () => {}
    const addProductInCart = () => {}

    const CartProductList = () => {
        return (
            client.cart.cartProductList.map(cartProduct => {
                return (
                    <div className={"row"}>
                        <div className={"column"}>{cartProduct.product.id}</div>
                        <div className={"column"}>{cartProduct.product.name}</div>
                        <div className={"column"}>{cartProduct.product.price}</div>
                        <div className={"column"}>
                            <input type={"number"} min={1} defaultValue={cartProduct.productCount}/>
                            <button onClick={changeProductCountInCart}>Изменить</button>
                        </div>
                        <div className={"column"}>
                            <button onClick={deleteProductInCart}>Удалить</button>
                        </div>
                    </div>
                )
            })
        )
    }

    return (
        <div>
            <div>
                id: <input type={"number"} min={1} defaultValue={1}/><button onClick={getClick}>Найти</button>
            </div>
            <div>Имя: {client.name}</div>

            <div className={"cart"}>
                <div className={"cart-title"}>Корзина</div>
                <div>Промокод: {client.cart.promoCode}</div>
                <div>Продукты:</div>
                <div className={"row row-title"}>
                    <div className={"column"}>id</div>
                    <div className={"column"}>Наименование</div>
                    <div className={"column"}>Цена</div>
                    <div className={"column"}>Количество</div>
                    <div className={"column"}></div>
                </div>
                <CartProductList/>
                <div className={"row"}>
                    <div className={"column"}>
                        <input type={"number"} min={1} defaultValue={1}/><button onClick={addProductInCart}>Добавить</button>
                    </div>
                    <div className={"column"}></div>
                    <div className={"column"}></div>
                    <div className={"column"}></div>
                    <div className={"column"}></div>
                </div>
            </div>
        </div>
    )
}
