
const newman = require('newman');

// get args from process. argv[0] & argv[1] are "node" and the name of the script to execute, respectively
const collection = process.argv[2];
const env = process.argv[3];

if(!(collection && env)) {
  printUsage();
  process.exit(1);
}

const runConfig = {
  collection: 'collections/' + collection + '.json',
  environment: 'env/' + env  + '.json',
  reporters: 'cli'
};

newman.run(runConfig, (err, summary, run) => {
  if (err) {
    console.log("ERROR", err);
    process.exit(1);
  }
  if (summary.run.failures) {
    console.log("FAILURE", summary.run.failures);
    process.exit(1);
  }
});

function printUsage(){
  console.log('USAGE: node test-runner.js <collection-name> <environment-name>');
}