requirejs.config( {
    // Path mappings for the logical module names  
    paths :  {
        'models' : 'models', knockout : 'libs/knockout/knockout-3.4.0.debug', jquery : 'libs/jquery/jquery-3.1.0', 'jqueryui-amd' : 'libs/jquery/jqueryui-amd-1.12.0', promise : 'libs/es6-promise/es6-promise', hammerjs : 'libs/hammer/hammer-2.0.8', ojdnd : 'libs/dnd-polyfill/dnd-polyfill-1.0.0', ojs : 'libs/oj/v2.2.0/debug', ojL10n : 'libs/oj/v2.2.0/ojL10n', ojtranslations : 'libs/oj/v2.2.0/resources', text : 'libs/require/text', css : 'libs/require-css/css', signals : 'libs/js-signals/signals'
    },
    // Shim configurations for modules that do not expose AMD  
    shim :  {
        'jquery' :  {
            exports : ['jQuery', '$']
        },
'crossroads' :  {
            deps : ['signals'], exports : 'crossroads'
        }
    },

   
    config :  {
        ojL10n :  {
            merge :  {
                
            }
        }
    }
});


 require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'jet-composites/amis-person/loader','jet-composites/amis-chart/loader'], // add additional JET components to the end of this list as needed  
function (oj, ko, $)// this callback gets executed when all required modules are loaded  
{
   

    function ViewModel() {
        var self = this;
        
        }

    ko.applyBindings(new ViewModel(), document.body);
})