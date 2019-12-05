const path = require("path");
const isProduction = process.env.NODE_ENV === 'production';
module.exports = {
    // 基本路径
    publicPath: './',
    //baseUrl: process.env.NODE_ENV === "production" ? "./" : "/"
    // 输出文件目录
    outputDir: 'dist',
    // eslint-loader 是否在保存的时候检查
    lintOnSave: false,
    // use the full build with in-browser compiler?
    // https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
    // compiler: false,
    // webpack配置
    // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md
    chainWebpack: config => {
        if(isProduction){
            // 删除预加载
            config.plugins.delete('preload');
            config.plugins.delete('prefetch');
            // 压缩代码
            config.optimization.minimize(true);
            // 分割代码
            config.optimization.splitChunks({
                chunks: 'all'
            })
        }
    },
    configureWebpack: config => {
        if (isProduction){
            let optimization= {
                splitChunks: {
                  cacheGroups: {
                    vendor:{
                      chunks:"all",
                          test: /node_modules/,
                          name:"vendor",
                          minChunks: 1,
                          maxInitialRequests: 5,
                          minSize: 0,
                          priority:100,
                    },
                    common: {
                      chunks:"all",
                      test:/[\\/]src[\\/]js[\\/]/,
                      name: "common",
                      minChunks: 2,
                      maxInitialRequests: 5,
                      minSize: 0,
                      priority:60
                    },
                    styles: {
                      name: 'styles',
                      test: /\.(le|sa|sc|c)ss$/,
                      chunks: 'all',
                      enforce: true,
                    },
                    runtimeChunk: {
                      name: 'manifest'
                    }
                  }
                }
            }
            Object.assign(config, {
                optimization
            })
        }
        // if (process.env.NODE_ENV === 'production') {
        //     // 为生产环境修改配置...
        //     config.mode = 'production';
        // } else {
        //     // 为开发环境修改配置...
        //     config.mode = 'development';
        // }
        // Object.assign(config, {
        //     // 开发生产共同配置
        //     resolve: {
        //         alias: {
        //             '@': path.resolve(__dirname, './src'),
        //         }
        //     }
        // });
        
    },
    // vue-loader 配置项
    // https://vue-loader.vuejs.org/en/options.html
    // vueLoader: {},
    // 生产环境是否生成 sourceMap 文件
    productionSourceMap: false,
    // css相关配置
    css: {
        // 是否使用css分离插件 ExtractTextPlugin
        extract: true,
        // 开启 CSS source maps?
        sourceMap: false,
        // css预设器配置项
        loaderOptions: {
            //为每个css引入公共需要引入的样式
            // less:{
            //     data:`@import "@/assets/public.less"`
            // }
        },
        // 启用 CSS modules for all css / pre-processor files.
        modules: false
    },
    // use thread-loader for babel & TS in production build
    // enabled by default if the machine has more than 1 cores
    parallel: require('os').cpus().length > 1,
    // 是否启用dll
    // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#dll-mode
    // dll: false,
    // PWA 插件相关配置
    // see https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa
    // pwa: {},
    // webpack-dev-server 相关配置
    devServer: {
        open: process.platform === 'darwin',
        host: '0.0.0.0',
        port: 8081,
        https: false,
        hotOnly: false,
        // 设置代理
        proxy: {
            '/ISmac/ismacsite': {
            target: 'http://127.0.0.1:8090', // 你接口的域名 http://192.168.16.26:8081  8090   172.18.47.162:8089
                secure: false, // 如果是https接口，需要配置这个参数
                changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
                pathRewrite:{
                '^/ISmac/ismacsite':'/ISmac/ismacsite'
                }
            }
        },
        before: app => {
        }
    },
    // 第三方插件配置
    pluginOptions: {}
}