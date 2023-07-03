export const ProductCreation = () => {
    const handlerClick = () => {

    }

    return (
        <div>
            <div>Наименование: <input type={"text"}/></div>
            <div>Цена: <input type={"number"} min={0} defaultValue={0}/></div>
            <div>Количество: <input type={"number"} min={0} defaultValue={0}/></div>
            <button onClick={handlerClick}>Создать</button>
        </div>
    )
}
