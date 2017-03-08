define(['ojs/ojcore', './amis-chart', 'text!./amis-chart.html', 'css!./amis-chart', 'ojs/ojcomposite'], function (oj, ComponentModel, view, css) {
    'use strict';
    var metadata = '{ "properties": { "chartData": { "type": "string"} } }';
    oj.Composite.register('amis-chart', 
    {
        metadata :  {
            inline : JSON.parse(metadata)
        },
        viewModel :  {
            inline : ComponentModel
        },
        view :  {
            inline : view
        },
        css :  {
            inline : css
        }
    });
});