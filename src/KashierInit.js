import { Platform, NativeModules } from "react-native";
import PropTypes from "prop-types";
const Kashier = NativeModules?.Kashier;

//TODO: Switch to ts after development is done

const initialize = async params => {
  const { merchantId, apiKey, currency, sdkMode, displayLang } = params;
  return Platform.select({
    android: await Kashier.init(
      merchantId,
      apiKey,
      currency,
      sdkMode,
      displayLang
    ),
    ios: (async () => {
      return await Kashier.getSdkMode();
    })()
  });
};
initialize.propTypes = PropTypes.shape({
  merchantId: PropTypes.string.isRequired,
  apiKey: PropTypes.string.isRequired,
  currency: PropTypes.string.isRequired,
  sdkMode: PropTypes.oneOf(["DEVELOPMENT", "PRODUCTION"]).isRequired,
  displayLang: PropTypes.oneOf(["EN", "AR"])
});
const KashierInit = {
  initialize
};
export { KashierInit };
