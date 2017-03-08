define(
    ['ojs/ojcore',
        './amis-person',
        'text!./amis-person.html',
        'css!./amis-person',
        'ojs/ojcomposite'],
    function (oj, ComponentModel, view, css) {
        'use strict';
        var metadata = '{ "properties": { "amisPersonName": { "type": "string"}, "amisPersonEmail": { "type": "string"}} }';
        oj.Composite.register('amis-person',
            {

                metadata: { inline: JSON.parse(metadata) },
                viewModel: { inline: ComponentModel },
                view: { inline: view },
                css: { inline: css }
            });
    }
);