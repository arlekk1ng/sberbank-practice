export const ProductChange = () => {
    const handlerClick = () => {

    }

    return (
        <div>
            <div>id: <input type={"number"} min={1} defaultValue={1}/></div>
            <div>Наименование: <input type={"text"}/></div>
            <div>Цена: <input type={"number"} min={0} defaultValue={0}/></div>
            <div>Количество: <input type={"number"} min={0} defaultValue={0}/></div>
            <button onClick={handlerClick}>Изменить</button>
        </div>
    )
}
