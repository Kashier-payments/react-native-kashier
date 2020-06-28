import { KashierConfig } from "KashierConfig";
import { KashierInit } from "KashierInit";
import { KashierServices } from "KashierServices";

const Kashier = {
  ...KashierConfig,
  ...KashierInit,
  ...KashierServices
};
export { Kashier };
