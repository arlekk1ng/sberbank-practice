export const Payment = () => {
    const handlerClick = () => {

    }

    return (
        <div>
            <div>client id: <input type={"number"} min={1} defaultValue={1}/></div>
            <div>Номер банк. карты: <input type={"text"}/></div>
            <button onClick={handlerClick}>Оплатить</button>
        </div>
    )
}
