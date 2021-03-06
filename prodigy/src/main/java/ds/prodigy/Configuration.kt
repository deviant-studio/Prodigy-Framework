package ds.prodigy

import ds.prodigy.component.IComponent

data class Configuration(val component: Class<out IComponent>, val presenter: Class<out Presenter<*>>, val layout: Int, val bindingVariable: Int) {
    constructor(component: Class<out IComponent>, presenter: Class<out Presenter<*>>, layout: Int) : this(component, presenter, layout, BR.presenter)
}
